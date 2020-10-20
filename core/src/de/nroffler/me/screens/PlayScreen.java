package de.nroffler.me.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Random;

import de.nroffler.me.box2dstatics.Shaped.CompleteVertical;
import de.nroffler.me.box2dstatics.Shaped.LShaped;
import de.nroffler.me.box2dstatics.StaticCollidableWorldObject;
import de.nroffler.me.box2dstatics.StaticDef;
import de.nroffler.me.box2dstatics.Shaped.StrictLShaped;
import de.nroffler.me.box2dworld.Player;
import de.nroffler.me.box2dworld.StartPlattform;
import de.nroffler.me.main.EndlessGame;
import de.nroffler.me.main.Statics;
import de.nroffler.me.tools.MyContactListener;
import de.nroffler.me.tools.MyInputPorcessor;

public class PlayScreen implements Screen {

    private EndlessGame endlessGame;
    private Player player;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private OrthographicCamera camera;
    private Viewport gameport;

    private float camer_should_be;

    private float currentx;
    private float currenty = 180 / Statics.PPM;

    private int progress = 1;
    private int module = 1;
    private int module_lenght = 1;

    private int last_module;

    private ArrayList<StaticDef> tocreate = new ArrayList<>();
    private ArrayList<StaticCollidableWorldObject> created = new ArrayList<>();

    public PlayScreen(EndlessGame endlessGame){
        this.endlessGame = endlessGame;

        world = new World(new Vector2(0, -4.4f), true);
        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera();
        gameport = new ExtendViewport(Statics.V_WIDTH / Statics.PPM, Statics.V_HEIGHT / Statics.PPM, camera);
        camera.position.set((gameport.getWorldWidth() / 2 + 200 ) / Statics.PPM,230 / Statics.PPM, 0);

        camer_should_be = camera.position.y;

        currentx = new Random().nextInt(2);
        if (currentx > 2){
            new StartPlattform(world, 330);
            player = new Player(this, 330);
        } else {
            new StartPlattform(world, 50);
            player = new Player(this, 50);
        }

        Gdx.input.setInputProcessor(new MyInputPorcessor(this));
        world.setContactListener(new MyContactListener(this));
    }

    @Override
    public void show() {
        System.out.println("---> PlayScreen");

        createnewPlattform();
        createnewPlattform();
    }

    private void queueSpawning(){
        if (!tocreate.isEmpty()){

            if (tocreate.get(0).type == LShaped.class){

                if (tocreate.get(0).meta == -1) {

                    created.add(new LShaped(getWorld(), tocreate.get(0).position.x, tocreate.get(0).position.y));
                    tocreate.remove(0);

                } else if (tocreate.get(0).meta == 0){

                    created.add(new LShaped(getWorld(), tocreate.get(0).position.x, tocreate.get(0).position.y));
                    tocreate.remove(0);

                }

            } else if (tocreate.get(0).type == StrictLShaped.class){

                created.add(new StrictLShaped(getWorld(), tocreate.get(0).position.x, tocreate.get(0).position.y));
                tocreate.remove(0);

            } else if (tocreate.get(0).type == CompleteVertical.class){

                created.add(new CompleteVertical(getWorld(), tocreate.get(0).position.x, tocreate.get(0).position.y));
                tocreate.remove(0);

            }

        }
    }

    private void queueDeleting(){
        for (int i = 0; i < created.size(); i++){
            if (created.get(i).getY() < camera.position.y-3f){
                created.get(i).destroy();
                created.remove(i);
            }
        }
    }

    @Override
    public void render(float delta) {

        queueSpawning();
        queueDeleting();

        player.update();
        if (player.getX() > 460 / Statics.PPM){
            player.body.setTransform(-60 / Statics.PPM, player.body.getPosition().y, player.body.getAngle());
        }
        if (player.getX() < -60 / Statics.PPM){
            player.body.setTransform(460 / Statics.PPM, player.body.getPosition().y, player.body.getAngle());
        }

        if (player.getY() < camera.position.y - camera.viewportHeight){
            endlessGame.setScreen(new SplashScreen(endlessGame));
        }

        if (camer_should_be > camera.position.y){
            camera.position.y += 0.1f;
            camera.update();
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, camera.combined);

        world.step(1 / 60f, 8, 3);
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }

    public World getWorld() {
        return world;
    }



    public void createnewPlattform(){
        System.out.println("Module: "+module+ " Modul Länge: "+module_lenght+ " active:" + (tocreate.size() + created.size()));
        camer_should_be = player.getY()+1.5f;

        if (currentx > 2) {
            currentx = 0.5f;
        } else if (currentx < 2){
            currentx = 3.5f;
        }

        if (module == 0){
            tocreate.add(new StaticDef(new Vector2(currentx, currenty), LShaped.class, -1));
            currenty += (140 + new Random().nextInt(40)) / Statics.PPM;
        } else if (module == 1){
            if (last_module == 1){
                System.out.println("y ++");
                currenty += (new Random().nextInt(80)+30) / Statics.PPM;
            }
            tocreate.add(new StaticDef(new Vector2(currentx, currenty), StrictLShaped.class, -1));
            currenty += 180 / Statics.PPM;
        } else if (module == 2){
            tocreate.add(new StaticDef(new Vector2(currentx, currenty), LShaped.class, 0));
            currenty += 100 / Statics.PPM;
        } else if (module == 3){
            tocreate.add(new StaticDef(new Vector2(currentx, currenty), CompleteVertical.class, 0));
            currenty += 200 / Statics.PPM;
        }


        progress++;
        if ((progress % module_lenght) == 0){

            last_module = module;

            module = new Random().nextInt(4);
            module_lenght = new Random().nextInt(3)+1;

            progress = 0;

            System.out.println("last+ "+last_module+ "current: "+module);
            if (last_module == 1 && module == 3){
                module = 1;
                System.out.println("unmögliches verhindert");
            }
        }

        if (tocreate.size() + created.size() < 6){
            createnewPlattform();
        }
    }




    public Player getPlayer(){
        return player;
    }
}
