# Juego de los Conejitos

En este proyecto explico cómo construí mi juego de conejitos en Android y de qué manera cumplí cada uno de los requisitos solicitados.

## Estructura de Activities

Mantengo la `MainActivity` original en inglés porque viene creada por Android Studio. Las pantallas adicionales que agregué manualmente están en español y cada una cumple una función específica:

- `InicioActivity` (`app/src/main/java/com/example/appconejitos/InicioActivity.java`): pantalla de inicio donde elijo la dificultad y puedo ir a las reglas.
- `ReglasActivity` (`app/src/main/java/com/example/appconejitos/ReglasActivity.java`): muestra las instrucciones del juego.
- `MainActivity` (`app/src/main/java/com/example/appconejitos/MainActivity.java`): contiene la lógica de la partida.
- `ResultadoActivity` (`app/src/main/java/com/example/appconejitos/ResultadoActivity.java`): presenta el mensaje final y permite reiniciar o volver al inicio.

## Diferentes contenedores (Layouts)

Utilicé distintos tipos de layouts para organizar la interfaz:

- `ConstraintLayout` en la pantalla principal (`app/src/main/res/layout/activity_main.xml`).
- `LinearLayout` en el inicio y en los resultados (`app/src/main/res/layout/activity_inicio.xml`, `app/src/main/res/layout/activity_resultado.xml`).
- `RelativeLayout` para las reglas (`app/src/main/res/layout/activity_reglas.xml`).

## Métodos que manipulan datos

En la `MainActivity` definí varios métodos que controlan el juego, por ejemplo `moverConejo`, que realiza el intercambio de posiciones:

```java
// app/src/main/java/com/example/appconejitos/MainActivity.java
private void moverConejo(int desde) {
    int vacio = posicionvacia();
    if (vacio == -1) return;
    int tagDesde = (int) conejos[desde].getTag();
    if (tagDesde == 0) return;
    // ... lógica de movimiento ...
}
```

## Uso de arreglos con mínimo 7 elementos

El tablero se basa en un arreglo de siete posiciones para los conejos:

```java
// app/src/main/java/com/example/appconejitos/MainActivity.java
private final int[] ids = {R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5, R.id.img6, R.id.img7};
```

## Funcionamiento y creatividad

El juego consiste en mover conejitos hacia el centro antes de que se acabe el tiempo. Utilicé un cronómetro (`CountDownTimer`) y recursos gráficos personalizados para dar una apariencia más atractiva.

## Rutas de archivos

- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/example/appconejitos/`
- `app/src/main/res/layout/`

Con esto demuestro que el proyecto cumple con todas las condiciones y que los archivos agregados manualmente están nombrados en español, manteniendo las actividades originales en inglés.
