%====================================================================================
% ddrboundary description   
%====================================================================================
%====================================================================================
context(ctxboundary, "localhost",  "TCP", "8125").
 qactor( boundaryworker, ctxboundary, "it.unibo.boundaryworker.Boundaryworker").
 static(boundaryworker).
