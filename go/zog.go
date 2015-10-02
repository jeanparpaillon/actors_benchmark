package main

import (
   "fmt"
   "os"
   "strconv"
   "time"
)

type Process struct {
	next chan int;
	previous chan int;
	end chan int;
}

func process(p *Process) {
	for {
		m := <-p.previous;
		switch m {
		case 0:
			p.end <- -1;
		default:
			p.next <- (m-1)
		}
	}
}

func main() {

	nr_processes, _ := strconv.Atoi(os.Args[1])
	nr_msgs, _ := strconv.Atoi(os.Args[2])

	init_t := time.Now()
	done_ch := make(chan int)

	start := new(Process)
	start.next = make(chan int)
	start.end = done_ch
	head := start
	for i := 1; i < nr_processes; i++ {
		p := new(Process)
		p.next = make(chan int)
		p.end = done_ch
		p.previous = head.next
		head = p
		go process(p)
	} 
	start.previous = head.next
	go process(start)
	// measure initialization time
	elapsedInit := time.Since(init_t)
    nanoseconds := elapsedInit.Nanoseconds()/int64(nr_processes)
    elapsedInitPerProcess := time.Duration(nanoseconds)

	run_t := time.Now()
	// go
    start.previous <- nr_msgs
    // just wait
    _ = <-done_ch
    elapsedRun := time.Since(run_t)
    nanoseconds = elapsedRun.Nanoseconds()/int64(nr_msgs)
    elapsedRunPerMsg := time.Duration(nanoseconds)
    
    // let's show some results
    fmt.Printf("initTime=%v, spawnTime=%v/proc, %d actors\n", elapsedInit, elapsedInitPerProcess, nr_processes)
    fmt.Printf("runTime=%v, msgTime=%v/msg, %d messages\n", elapsedRun, elapsedRunPerMsg, nr_msgs)
}
