started.

+started : true
<- .print("Hello World!");
    !move.

+!move : distanciaFrente(X) & X<10
<-  iaLib.tras;
    !move.
+!move : distanciaFrente(X) & X>10
<-  iaLib.frente;
    !move.
+!move <- !move.

+distanciaFrente(X) <- .print("Distancia Frente: ",X).