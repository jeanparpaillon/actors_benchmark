class Zog extends Thread {
    private int id;
    private Zog next;
    private boolean flag;
    private int message;

    Zog(int n) {
	id = n;
	flag = false;
    }

    public void link(Zog zog) {
	next = zog;
    }

    public void run() {
	try {
	    do this.relay(); while (message > 0);
	} catch (InterruptedException e) {}
    }
  
    private synchronized void relay() throws InterruptedException {
	while (flag == false)
	    wait();
	flag = false;
	next.send(message - 1);
    }

    public synchronized void send(int n) throws InterruptedException {
	message = n;
	flag = true;
	notify();
    }

    public static void main(String args[]) {
	int n = Integer.parseInt(args[0]);
	int m = Integer.parseInt(args[1]);
	Zog old;
	First first = new First(n, m);

	first.start();

	old = first;
	while (--n > 0) {
	    Zog curr = new Zog(n);

	    curr.link(old);
	    curr.start();
	    old = curr;
	}

	first.link(old);

	try {
	    first.send(m);
	} catch (InterruptedException e) {}
    }
}
