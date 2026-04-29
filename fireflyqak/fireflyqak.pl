%====================================================================================
% fireflyqak description   
%====================================================================================
mqttBroker("localhost", "1883", "lifegameIn").
dispatch( cellstate, cellstate(R,C,COLOR) ).
%====================================================================================
context(ctxfirefly, "localhost",  "TCP", "8010").
context(ctxgrid, "127.0.0.1",  "TCP", "8050").
 qactor( griddisplay, ctxgrid, "external").
  qactor( firefly, ctxfirefly, "it.unibo.firefly.Firefly").
 static(firefly).
  qactor( griddisplaymock, ctxfirefly, "it.unibo.griddisplaymock.Griddisplaymock").
 static(griddisplaymock).
