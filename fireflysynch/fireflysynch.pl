%====================================================================================
% fireflysynch description   
%====================================================================================
dispatch( cellstate, cellstate(X,Y,COLOR) ). %commute cell state
event( frequency, frequency(F) ).
event( timer, timer(T) ).
%====================================================================================
context(ctxfireflysynch, "localhost",  "TCP", "8010").
context(ctxgrid, "127.0.0.1",  "TCP", "8050").
 qactor( griddisplay, ctxgrid, "external").
  qactor( creator, ctxfireflysynch, "it.unibo.creator.Creator").
 static(creator).
  qactor( firefly, ctxfireflysynch, "it.unibo.firefly.Firefly").
dynamic(firefly). %%Oct2023 
