let liniowa x=(2.0*.x+.2.0);;
let przeksztalcona x = (liniowa x)/.3.0 +.1.0;;

let rec calkujHelper (funkcja, (h:float), (a:float), (n:float), (acc:float)) =
if (n<0.1) then acc
else
calkujHelper (funkcja, h, a, n-.1.0, acc+.((funkcja (a+. n*.h) +. funkcja (a+.(n-.1.0)*.h))*.h/.2.0));;

let calkuj funkcja ((a: float ),(b: float)) (n:float) =
calkujHelper (funkcja, (b-.a)/.n, a, n , 0.0);;


calkuj liniowa (0.0,5.0) (-.5.0);;
calkuj liniowa (0.0,5.0) 0.0;;
calkuj liniowa (0.0,5.0) 2.0;;
calkuj liniowa (0.0,5.0) 10.0;;
calkuj liniowa (5.0,15.0) 10.0;;
calkuj liniowa (-.5.0,5.0) 10.0;;
calkuj liniowa (-.5.0,0.0) 10.0;;


calkuj przeksztalcona (0.0,5.0) 20.0;;
calkuj przeksztalcona (0.0,0.0) 20.0;;