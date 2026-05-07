%====================================================================================
% ddrsystem description   
%====================================================================================
event( radar, distance(D) ).
%====================================================================================
context(ctxddrsys, "localhost",  "TCP", "8010").
 qactor( radar, ctxddrsys, "it.unibo.radar.Radar").
 static(radar).
  qactor( mind, ctxddrsys, "it.unibo.mind.Mind").
 static(mind).
