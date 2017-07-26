package rs.ac.uns.ftn.nansi.factories;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import lombok.val;
import rs.ac.uns.ftn.nansi.util.Factory;

public class Box2DFactory implements Factory<Body> {


    private final World world;
    private final Vector2 pos;

    public Box2DFactory(World world, Vector2 pos) {

        this.world = world;
        this.pos = pos;
    }

    @Override
    public Body create() {
        val bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(pos);

        val body = world.createBody(bodyDef);
        val circle = new CircleShape();
        circle.setRadius(0.5f);

        val fixtureDef = new FixtureDef();
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
