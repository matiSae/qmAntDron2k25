# Requisitos

## REQ01

Iniciar el sistema solicitando una autenticacion(usuario y contraseña; agregar "matias","123") con maximo 3 intentos. Si la autenticacion es válida se debe presentar cédula y nombre completo.

## REQ02

El antomologo debe cosechar (segun su caso de estudio) desde el storage hormigas y comida mediante un proceso de ETL(Extract, transition,LoadDatabase ). Durante el proceso se debe mostra Loading/Waiting por cada dato. Los datos validos se muestran de color azul y se almacenan solo los datos de su caso de estudio; los datos con error se muestran en rojo.

## REQ03

Implementar IEntomologo y alimentar sus hormigas almacenadas en la base de datos, considerando:

-preparar(Alimento)
-La hormiga Vive si come su alimento con o sin genoma caso contrario Muere; el alimento comido debe ser eliminado.
-HLarva al comer su tipo de alimento con o sin genoma se transforma en su tipo de hormiga y cambia de sexo acorde al genoma. Si el genoma corresponde a su caso de estudio se habilita la SuperHabilidad

## REQ04

Diseñar un metodo a su Hormiga para la superHabilidad

## REQ05

Se debe garantizar que la accion preparar(Alimento):Alimento sea realizado solo por el entomólogo o el entomólogo genetista.El entomologo genetista prepara el alimento inyectando un genoma aleatoriamente: (X, XX, XY ) imprimir: " [Preparando]-`tipoAlimento + Genoma`-"; mientras el entomologo prepara el alimento sin genomas e imprimir:"[Preparado]-`tipoAlimento`-"
