## JavaFX App with Multiple Scenes

You can change the Scene shown in a window (stage).

If you have a reference to the Stage, just do
something like:
```java
Parent root = initComponents();
Scene scene2 = new Scene(root);
stage.setScene(scene2);
stage.sizeToScene();
stage.show();
```

But if the user performs some actual to change the
scene, the action will be handled in the controller
of the current scene.  That controller usually does
not have a reference to the stage.

You could fix this in 3 steps:

1. in your Main application class (where the `start(Scene scene)` method is, get a reference to the controller.
2. set a reference to the Stage into the controller.
3. in the controller, use the Stage to initialize and show a new scene when the user requests it

The downside of this is that its increasing coupling
between objects.  The Controller for scene1 needs
to know how to create scene2.  If scene2's controller
also needs to change the scene then that's even more coupling.

This example shows a way without much coupling.
Its a simplified version of the *Mediator Design Pattern*.

Here are the parts:

| Class/Package  | Description        |
|----------------|:-------------------|
| Main           | Main Application class |
| main.fxml      | Simple scene with a menu |
| MainController | Add menu items for the games |
| guessinggame/  | Guesssing Game in JavaFX. This code can run by itself. |
| tictactoe/     | Tic-tac-toe game in JavaFX. This code can run by itself. |
| util/StageManager | Class to manage showing scenes on a single Stage. |

To compile everything into `bin/` directory:

```
> mkdir bin
> cd src
> javac -d ../bin -cp /path/to/javafx/lib/*:. Main.java
```

To run on command line: 

```
> cd bin
> java --module-path /path/to/javafx/lib --add-modules javafx.fxml,javafx.controls Main
```

On the Main scene choose any game. In the Game scene, you can go back to main.
You can go back to main in the middle of a game, and the game state is preserved -- come back and resume playing.

### How it Works

`StageManager` in a singleton and keeps a reference to the stage.  It also keeps a reference to scenes and can show a scene on the stage.  In the `start(Stage stage)` method we do:

```java
StageManager.getInstance().setStage(stage);
```
now StageManager knows the stage.

We also tell stage manager to "remember" the main scene we just created:

```java
Scene scene = new Scene(root);
StageManager.getInstance().register("main", scene);
```

Anytime we want to show the "main" scene again, just enter:
```java
StageManager.getInstance().showScene("main");
```

I added code to StageManager to create some scenes if they are not pre-registered.  That way I don't need to create every scene initially.

Switching to a new game:

There is a menu item to switch to the Tic-tac-toe game.
Just create the menu item (or get it from FXML) and add
an event handler to switch scenes:

```java
MenuItem item = new MenuItem("Tic-tac-toe");
// Event Handler written as Lambda expression:
Handler<ActionEvent> playTicTacToe = (e) -> StageManager.getInstance().showScene("tictactoe");
// add the event handleer
item.setOnAction( playTicTacToe );
```
Of course, you also have to add the MenuItem to menu.

### Advantages of Mediator

It reduces coupling between objects.  The Controller doesn't need a reference
to the Stage, and doesn't need a reference to other scenes.


## Another Approach

There are other ways to "change" the scene without creating a new Scene.

1. **Change Components Within A Layout** - you can remove components from a layout and replace them with new ones.
2. **Use a TabPane** - each tab is a separate container.  

Both these approaches are simpler.  You have only one controller that knows all the components.  That can simplify passing of information and actions performed by event handlers.
