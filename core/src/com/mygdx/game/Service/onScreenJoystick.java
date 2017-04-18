package com.mygdx.game.Service;

/**
 * Created by Adrian on 2017-04-18.
 */

public class onScreenJoystick {

    //Create a touchpad skin
    touchpadSkin = new Skin();
    //Set background image
    touchpadSkin.add("touchBackground", new Texture("data/touchBackground.png"));
    //Set knob image
    touchpadSkin.add("touchKnob", new Texture("data/touchKnob.png"));
    //Create TouchPad Style
    touchpadStyle = new TouchpadStyle();
    //Create Drawable's from TouchPad skin
    touchBackground = touchpadSkin.getDrawable("touchBackground");
    touchKnob = touchpadSkin.getDrawable("touchKnob");
    //Apply the Drawables to the TouchPad Style
    touchpadStyle.background = touchBackground;
    touchpadStyle.knob = touchKnob;
    //Create new TouchPad with the created style
    touchpad = new Touchpad(10, touchpadStyle);
    //setBounds(x,y,width,height)
    touchpad.setBounds(15, 15, 200, 200);

    //Create a Stage and add TouchPad
    stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, batch);
    stage.addActor(touchpad);
    Gdx.input.setInputProcessor(stage);

    //Create block sprite
    blockTexture = new Texture(Gdx.files.internal("data/block.png"));
    blockSprite = new Sprite(blockTexture);
    //Set position to centre of the screen
    blockSprite.setPosition(Gdx.graphics.getWidth()/2-blockSprite.getWidth()/2, Gdx.graphics.getHeight()/2-blockSprite.getHeight()/2);

    blockSpeed = 5;
}




        //Move blockSprite with TouchPad
        blockSprite.setX(blockSprite.getX() + touchpad.getKnobPercentX()*blockSpeed);
        blockSprite.setY(blockSprite.getY() + touchpad.getKnobPercentY()*blockSpeed);

        //Draw

}
