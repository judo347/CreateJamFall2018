package dk.amminiti.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.screens.GameScreen;
import dk.amminiti.screens.OrthographicTargetedCamera;

public class UiManager {

    private Stage stage;
    private GameScreen screen;
    private Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    private class PlayerData{

        private Image primaryAbility = new Image(new TextureRegion(new Texture("energydrinks/cult.png")));
        private Image secondaryAbility = new Image(new TextureRegion(new Texture("energydrinks/blank.png")));
        private Label primaryLevel;
        private Label secondaryLevel;

        private ProgressBar secondaryProcessBar;

        private Table contentTable;
        private Skin skin;
        private String playerName;

        public PlayerData(String playerName, Skin skin) {
            contentTable = new Table();
            this.playerName = playerName;
            this.skin = skin;
            primaryLevel = new Label("1", skin);
            secondaryLevel = new Label("0", skin);
            initialize(secondaryAbility, secondaryLevel, 0, 0f, 0f);
        }

        private void initialize(Image secondaryImage, Label secondaryLabel, int currentMana, float primaryCooldown, float secondaryCooldown){
            contentTable.clearChildren();

            contentTable.add(new Label(playerName, skin)).row();
            contentTable.add(getAbilitiesPanel(secondaryImage, secondaryLabel, currentMana, primaryCooldown, secondaryCooldown));

        }

        private Table getAbilitiesPanel(Image secondaryImage, Label secondaryLabel, int currentMana, float primaryCooldown, float secondaryCooldown){

            Table table = new Table();

            table.add(getAbilityCell(primaryAbility, primaryLevel, currentMana, primaryCooldown));
            table.add(getAbilityCell(secondaryImage, secondaryLabel, currentMana, secondaryCooldown));

            return table;
        }

        private Table getAbilityCell(Image image, Label levelLabel, int currentMana, float cooldown){

            Table table = new Table();

            if(image != primaryAbility){
                secondaryProcessBar = new ProgressBar(0, 100, 25, false, skin);
                secondaryProcessBar.setValue(currentMana);

                table.add(secondaryProcessBar).row();
            }else{
                table.add(new Label("", skin)).row();
            }



            table.add(image).row();
            table.pad(10);
            table.add(levelLabel);

            float cooldownLeft = (cooldown > 0) ? cooldown : 0f;
            String cooldownLeftString = String.format("%.2f", cooldownLeft);

            table.add(new Label(cooldownLeftString, skin));

            return table;
        }

        public Table getContentTable() {
            return contentTable;
        }

        protected void update(Player player){
            if(player.getSpell() != null){
                initialize(new Image(EnergyDrink.getTexture(player.getSpell().getType())), new Label(String.valueOf(player.getSpellLevel()), skin), (int)player.getMana(), player.getCultSpell().getCooldownLeft(), player.getSpell().getCooldownLeft());
            }else
                initialize(secondaryAbility, secondaryLevel, 0, player.getCultSpell().getCooldownLeft(), 0);
        }
    }

    private PlayerData playerOneUi;
    private PlayerData playerTwoUi;

    public UiManager(GameScreen screen) {
        this.screen = screen;

        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = screen.getCamera().position.x;
        camera.position.y = screen.getCamera().position.y;

        stage = new Stage(new ScreenViewport(camera));


        setUpUi();
    }

    private void setUpUi(){
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        this.playerOneUi = new PlayerData("Player 1", skin);
        this.playerTwoUi = new PlayerData("Player 2", skin);

        Table table = new Table();
        table.setFillParent(true);
        table.bottom();
        table.add(playerOneUi.getContentTable());
        table.add(playerTwoUi.getContentTable());

        stage.addActor(table);
    }

    public void render(OrthographicTargetedCamera camera, Player p1, Player p2){

        //stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        //stage.getViewport().setCamera(camera);
        //stage.getViewport()
        //stage.getViewport().

        //stage.getViewport().setScreenPosition((int)camera.position.x, (int)camera.position.y);

        //stage.getViewport().setScreenPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 );

        //stage.getViewport().setScreenHeight(Gdx.graphics.getHeight() * 4);
        //stage.getViewport().setScreenSize(Gdx.graphics.getWidth() * 4, Gdx.graphics.getHeight() * 4);

        playerOneUi.update(p1);
        playerTwoUi.update(p2);

        stage.act();
        stage.draw();
    }

}
