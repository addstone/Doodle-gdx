package net.digaly.doodle.test;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.digaly.doodle.Entity;
import net.digaly.doodle.InputMode;
import net.digaly.doodle.Point;
import net.digaly.doodle.events.KeyState;
import net.digaly.doodle.events.MouseState;
import net.digaly.doodle.events.listeners.KeyEventListener;
import net.digaly.doodle.events.listeners.MouseEventListener;
import net.digaly.doodle.events.listeners.UpdateListener;
import net.digaly.doodle.rendering.AnimatedSprite;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static javafx.scene.input.KeyCode.Z;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class PlayerEntity extends Entity implements KeyEventListener, UpdateListener, MouseEventListener
{
    private double speed;
    private int turnSpeed = 5;

    private Texture spriteNone;
    private Texture spriteFlying;
    private Texture spriteBullet;

    private Random random;

    private int shootDelay;
    private int bulletPower;
    private double bulletSpread;

    public PlayerEntity(double x, double y)
    {
        super(new Sprite(new Texture("ship.png")), 0, 0);
        Texture shipTexture = new Texture("ship.png");
        shipTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        setDepth(10);

        Texture bulletTexture = new Texture("bullet.png");
        shipTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        //spriteNone = new Sprite("ship_n.png");
        spriteNone = shipTexture;
        spriteFlying = shipTexture; //new AnimatedSprite(new String[] {"v2\\ship_flyingA.png", "v2\\ship_flyingB.png"}, 5);
        spriteBullet = bulletTexture;

        getSprite().setTexture(spriteNone);
        setSprite(new AnimatedSprite(new Texture("cow_sheet.png"), 64, 64, 4, 1, 0.1f, true));
        //getSprite().setColor(com.badlogic.gdx.graphics.Color.RED);

        random = new Random();

        shootDelay = 0;
        speed = 0;
        bulletPower = 360;
        bulletSpread = 360;
    }

    private void shoot()
    {
        if (shootDelay == 0)
        {
            int angleSwing;

            for (int i = 0; i < bulletPower; i++)
            {
                angleSwing = (int) -(bulletSpread / 2) + random.nextInt((int) bulletSpread);
                getRoom().addEntity(new BulletEntity(new Sprite(spriteBullet), getSprite().getX() + 16, getSprite().getY() + 16, getAngle() + angleSwing, 10));
            }

            shootDelay = 5;
            //getRoom().getSoundManager().playSound("DoodleSample\\res\\shoot.wav");
        }
    }

    @Override
    public void onKeyEvent(net.digaly.doodle.events.KeyEvent keyEvent)
    {
        if (keyEvent.getKeyState() == KeyState.HOLDING) {
            switch (keyEvent.getKeyCode())
            {
                case Input.Keys.Z:
                    speed += 0.2;
                    getSprite().setTexture(spriteFlying);
                    break;
                case Input.Keys.S:
                    speed -= 0.2;
                    getSprite().setTexture(spriteFlying);
                    break;
                case Input.Keys.Q:
                    setAngle(getAngle() - turnSpeed);
                    break;
                case Input.Keys.D:
                    setAngle(getAngle() + turnSpeed);
                    break;
                case Input.Keys.SPACE:
                    shoot();
                    break;
                case Input.Keys.P:
                    getRoom().getApplicationContext().setInputMode(InputMode.STAGE);
                    break;
            }
        }
    }

    @Override
    public void onUpdate()
    {
        /*Camera camera = getRoom().getRenderer().getCamera();
        Group root = getRoom().getRenderer().getRoot();

        double camTargetX = getPosition().x - root.getScene().getWidth() / 2;
        double camTargetY = getPosition().y - root.getScene().getHeight() / 2;

        if (camTargetX > 0 && camTargetX < getRoom().getSize().getWidth() - root.getScene().getWidth()) {
            camera.setTranslateX(camTargetX);
        }

        if (camTargetY > 0 && camTargetY < getRoom().getSize().getHeight() - root.getScene().getHeight()) {
            camera.setTranslateY(camTargetY);
        }*/

        double targetTranslateX = Math.cos(getAngle() * 0.017) * speed;
        double targetTranslateY = Math.sin(getAngle() * 0.017) * speed;

        //if (getPosition().x + targetTranslateX > 0 && getPosition().x + targetTranslateX < getRoom().getSize().getWidth()) {
        getSprite().setX(getSprite().getX() + (float) (Math.cos(getAngle() * 0.017) * speed));
        getSprite().setY(getSprite().getY() + (float) (Math.sin(getAngle() * 0.017) * speed));
        //}

        //if (getPosition().y + targetTranslateY > 0 && getPosition().y + targetTranslateY < getRoom().getSize().getHeight()) {
             //setPosition(new Point(getPosition().x, getPosition().y + (float) (Math.sin(getAngle() * 0.017) * speed)));
        //}


        //Speed limiting
        if (speed > 6)
        {
            speed = 6;
        }

        if (speed < -5)
        {
            speed = -5;
        }

        //Slowing down
        if (speed > 0) speed -= 0.1;
        if (speed < 0) speed += 0.1;

        if (Math.abs(speed) < 0.1) speed = 0;

        turnSpeed = 5 - (int) speed / 2;

        //getRoom().addEntity(new TrailEntity(getSprite(), getPosition().x, getPosition().y, getAngle(), 0.2, 0.01));

        getSprite().setTexture(spriteNone);

        if (shootDelay > 0) shootDelay -= 1;

        getRoom().getApplicationContext().getRenderer().getCamera().position.set(getSprite().getX(), getSprite().getY(), 0);
    }

    @Override
    public void onMouseEvent(net.digaly.doodle.events.MouseEvent mouseEvent)
    {
        if (mouseEvent.getMouseState() == MouseState.HOLDING) {
            if (mouseEvent.isInBoundsOfRectangle(getSprite().getBoundingRectangle())) {
                System.out.println(mouseEvent.getButton());
            }
        }
    }
}
