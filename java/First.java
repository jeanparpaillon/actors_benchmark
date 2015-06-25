class First extends Zog {
    private int procs, msgs;
    private long t1, t2, t3;

    First(int n, int m) {
	super(n);
	procs = n;
	msgs = m;
    }

    public void run() {
	long run_time, init_time;
	double msg_time, spawn_time;

	t1 = System.currentTimeMillis();
	super.run();
	t3 = System.currentTimeMillis();

	init_time = t2 - t1;
	spawn_time = (1000.0 * init_time) / procs;
	System.out.println("init_time = " + init_time + " ms (" +
			   spawn_time + " us/proc) (" + procs + " procs)");

	run_time = t3 - t2;
	msg_time = (1000.0 * run_time) / msgs;
	System.out.println("run_time = " + run_time + " ms (" +
			   msg_time + " us/msg) (" + msgs + " msgs)");
    }

    public void link(Zog zog) {
	super.link(zog);
	t2 = System.currentTimeMillis();
    }
}
