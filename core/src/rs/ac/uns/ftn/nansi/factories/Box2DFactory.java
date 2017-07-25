package rs.ac.uns.ftn.nansi.factories;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Box2DFactory {

    public static Body createCircle(World world, Vector2 pos) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(pos);
        Body body = world.createBody(bodyDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(0.5f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        fixtureDef.filter.maskBits = 0x0000;
        body.createFixture(fixtureDef);
        circle.dispose();
        return body;
    }

}
