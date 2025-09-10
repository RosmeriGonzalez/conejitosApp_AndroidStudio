# Juego de Conejitos

Este proyecto fue desarrollado en Android Studio y se compone de varias pantallas (Activities) y layouts que dan vida a un juego de lógica con conejos.

## Cumplimiento de requisitos

### Uso de múltiples Activities
Para dividir la aplicación en pantallas con responsabilidades claras utilicé cuatro Activities:

- **`StartActivity`** (`app/src/main/java/com/example/appconejitos/StartActivity.java`): pantalla de inicio donde elijo la dificultad y puedo acceder a las reglas.
- **`RulesActivity`** (`app/src/main/java/com/example/appconejitos/RulesActivity.java`): muestra las instrucciones del juego.
- **`MainActivity`** (`app/src/main/java/com/example/appconejitos/MainActivity.java`): contiene la partida principal.
- **`ResultadoActivity`** (`app/src/main/java/com/example/appconejitos/ResultadoActivity.java`): muestra el mensaje final. Este archivo fue nombrado en español porque lo añadí manualmente; en cambio, `MainActivity` conserva su nombre por ser parte del proyecto inicial.

### Implementación de diferentes contenedores (Layouts)
Cada pantalla utiliza un tipo de Layout distinto:

- `activity_main.xml` usa **ConstraintLayout** para organizar los conejos y el temporizador.
- `activity_start.xml` y `activity_resultado.xml` usan **LinearLayout** para colocar elementos uno debajo del otro.

### Aplicación de métodos
El juego se apoya en varios métodos para manejar la lógica. Un ejemplo es `moverConejo`, encargado de validar y ejecutar cada salto:

```java
// app/src/main/java/com/example/appconejitos/MainActivity.java
private void moverConejo(int desde) {
    int vacio = posicionvacia();
    if (vacio == -1) return;
    int tagDesde = (int) conejos[desde].getTag();
    if (tagDesde == 0) return;
    // Lógica de movimiento
}
```

### Uso de arreglos con mínimo 7 elementos
Para identificar cada imagen del tablero utilizo un arreglo de siete elementos:

```java
// app/src/main/java/com/example/appconejitos/MainActivity.java
private final int[] ids = {R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5, R.id.img6, R.id.img7};
```

### Funcionalidad y creatividad
El juego consiste en intercambiar posiciones de los conejos hasta que todos queden en el lado opuesto. Incluí un temporizador y un botón de reinicio para incrementar el desafío.

### Logo en la pantalla de inicio
Agregué el logo del proyecto (`app/src/main/res/drawable/logo.png`) en la pantalla inicial mediante un `ImageView` en `activity_start.xml`.

## Ejecución de pruebas
Para verificar la compilación del código ejecuté:

```bash
./gradlew test
```

---
*Repositorio: [conejitosApp_AndroidStudio](./)*
