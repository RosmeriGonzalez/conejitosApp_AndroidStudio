# Juego de Conejitos

Este es mi proyecto final de Android Studio donde programé un pequeño juego de lógica con conejos. A continuación explico, en primera persona, cómo cumplí cada uno de los requisitos del trabajo.

## Uso de múltiples Activities (20%)
Creé varias pantallas, cada una con una responsabilidad clara:

- **Inicio** (`app/src/main/java/com/example/appconejitos/StartActivity.java`): aquí elijo la dificultad y puedo ir a las reglas. El siguiente fragmento muestra cómo paso el nivel seleccionado a la partida:

```java
// app/src/main/java/com/example/appconejitos/StartActivity.java
btnJugar.setOnClickListener(v -> {
    int tiempo;
    int selected = rgDificultad.getCheckedRadioButtonId();
    if (selected == R.id.rbMedio) {
        tiempo = 30000;
    } else if (selected == R.id.rbDificil) {
        tiempo = 15000;
    } else {
        tiempo = 60000;
    }
    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("tiempo", tiempo);
    startActivity(intent);
});
```

- **Reglas** (`app/src/main/java/com/example/appconejitos/RulesActivity.java`): únicamente muestra las instrucciones y un botón para regresar al inicio.
- **Partida** (`app/src/main/java/com/example/appconejitos/MainActivity.java`): contiene la lógica del tablero y el temporizador.
- **Resultados** (`app/src/main/java/com/example/appconejitos/ResultadoActivity.java`): despliega el mensaje final y ofrece reiniciar o volver al menú.

## Implementación de diferentes contenedores (Layouts) (15%)
Para organizar la interfaz utilicé varios tipos de Layouts:

- `app/src/main/res/layout/activity_main.xml` inicia con un **ConstraintLayout** que me permite posicionar el tablero y el cronómetro:

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
```

- `app/src/main/res/layout/activity_start.xml` usa un **LinearLayout** vertical donde coloqué el logo y las opciones de nivel:

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">
```

## Aplicación de métodos (15%)
Implementé varios métodos para manejar los datos del juego. El más importante es `moverConejo`, que valida y realiza cada movimiento:

```java
// app/src/main/java/com/example/appconejitos/MainActivity.java
private void moverConejo(int desde) {
    int vacio = posicionvacia();
    if (vacio == -1) return;
    int tagDesde = (int) conejos[desde].getTag();
    if (tagDesde == 0) return;
    // Reglas de movimiento y salto
}
```

## Uso de arreglos con mínimo 7 elementos (15%)
Para referenciar cada imagen del tablero empleé un arreglo con siete identificadores de vista:

```java
// app/src/main/java/com/example/appconejitos/MainActivity.java
private final int[] ids = {R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5, R.id.img6, R.id.img7};
```

## Funcionalidad del juego (20%)
El objetivo es que los conejos de la izquierda salten hacia la derecha y viceversa antes de que se acabe el tiempo. Utilizo un `CountDownTimer` para controlar el límite y métodos como `hayMovimientos()` y `juegoGanado()` para determinar el estado de la partida.

## Creatividad y presentación (15%)
Además de las imágenes de los conejos y las piedras, personalicé la presentación con un fondo propio (`app/src/main/res/drawable/fondo.png`) y agregué un logo en la pantalla de inicio.

### Logo en la pantalla de inicio
Inserto el archivo `app/src/main/res/drawable/logo.png` mediante un `ImageView` colocado al inicio de `app/src/main/res/layout/activity_start.xml`:

```xml
<ImageView
    android:id="@+id/imgLogo"
    android:layout_width="262dp"
    android:layout_height="117dp"
    android:src="@drawable/logo" />
```

## Ejecución de pruebas
Para asegurarme de que el proyecto compila ejecuté:

```bash
./gradlew test
```

---
Repositorio: `conejitosApp_AndroidStudio`

