#
# 
#
import sys,time
import pykka

def ustime():
    return int(round(time.time() * 1000 * 1000))

TYPE_M = 0
TYPE_STOP = 1

class Zog(pykka.ThreadingActor):
    def __init__(self, prev=None):
        super(Zog, self).__init__()
        self.prev = prev
        print "%s -> %s" % (self, prev)

    def on_msg(self, m):
        if m > 0:
            print "<2> %s ! {%d, %s}" % (self.prev, m, self)
            self.prev.tell({'type': 1, 'm': m-1})
        else:
            print "<3>"
            self.stop()

    def on_receive(self, msg):
        if msg['type'] == TYPE_STOP:
            self.prev.tell(msg)
            self.stop()
            return

        self.on_msg(msg['m'])

class First(Zog):
    def __init__(self, n=0, m=0):
        super(First, self).__init__(prev=self)
        self.n = n
        self.m = m

    def on_start(self):
        start_time = ustime()
        prev = self.actor_ref
        i = self.n-1
        while i > 0:
            prev = Zog.start(prev=prev)
            i -= 1
        end_time = ustime()
        duration = end_time - start_time
        print "Setup : %f s (%d us per spawn) (%d spawns)" % ((duration / 1000 / 1000), duration / (self.n-1), self.n-1)
        self.prev = prev
        
def main():
    n = int(sys.argv[1])
    m = int(sys.argv[2])

    first = First.start(n=n, m=m)
    
    start_time = ustime()
    first.ask({'type': TYPE_M, 'm': m})
    end_time = ustime()
    duration = end_time - start_time
    print "Run   : %d s (%d us per msg) (%d msgs)" % ((duration / 1000 / 1000), duration / self.m, self.m)
    
    prev.ask({'type': TYPE_STOP})
    

if __name__ == '__main__':
    main()
