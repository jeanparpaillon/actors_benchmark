-module(zog).

%% This is a test program that first creates N processes (that are
%% "connected" in a ring) and then sends M messages in that ring.
%%
%% - September 1998
%% - roland


-export([start/0, start/1, start/2]).

-export([run/3, process/1]).			% Local exports - ouch

start() -> start(16000).

start(N) -> start(N, 1000000).

start(N, M) -> 
    Pid = spawn(?MODULE, run, [N, M, self()]),
    receive
	{stop, Pid} -> halt()
    end.

run(N, _, _) when N < 1 ->
    io:format("Must be at least 1 process~n", []),
    0.0;
run(N, M, Main) ->
    statistics(wall_clock),

    Pid = setup(N-1, self()),

    {_,T1} = statistics(wall_clock),
    io:format("Setup : ~w s", [T1/1000]),
    case N of
	1 -> io:format(" (0 spawns)~n", []);
	_ -> io:format(" (~w us per spawn) (~w spawns)~n",
		       [1000*T1/(N-1), N-1])
    end,
    statistics(wall_clock),

    Pid ! M,
    K = process(Pid),

    {_,T2} = statistics(wall_clock),
    Time = 1000*T2/(M+K),
    io:format("Run   : ~w s (~w us per msg) (~w msgs)~n",
	      [T2/1000, Time, (M+K)]),
    Main ! {stop, self()}.
    

setup(0, OldPid) ->
    OldPid;
setup(N, OldPid) ->
    NewPid = spawn(?MODULE, process, [OldPid]),
    setup(N-1, NewPid).


process(Pid) ->
    receive
	M ->
	    Pid ! M-1,
	    if
		M < 0  -> -M;
		true   -> process(Pid)
	    end
    end.
