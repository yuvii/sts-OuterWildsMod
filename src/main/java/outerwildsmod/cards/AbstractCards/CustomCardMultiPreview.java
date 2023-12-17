package outerwildsmod.cards.AbstractCards;

// multi-card preview copied from: https://github.com/H2WO4/CustomCardMultiPreview
// https://github.com/mikemayhemdev/EvilWithin/blob/02ff1bef435dcf12178c871ec25ad5f91ed640dd/src/main/java/automaton/cards/TinkerersToolbox.java

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public abstract class CustomCardMultiPreview extends CustomCard {

    public ArrayList<AbstractQuantumCard> linkedCards = new ArrayList<>();
    private final int[] xSingle = new int[]{0, -280, -280};
    private final int[] xSingle4more = new int[]{0, -280, -280, -280, 0};
    private final int[] ySingle = new int[]{0, 0, -360};
    private final int[] ySingle4more = new int[]{100, 100, -260, -620, -620};
    private final int[] xMulti = new int[]{0, 0, 285, 570, 570};
    private final int[] yMulti = new int[]{0, 360, 360, 360, 0};

    private float rotationTimer;
    private int previewIndex;

    public boolean showPreview;

    public CustomCardMultiPreview(final String id,
                                  final String name,
                                  final String img,
                                  final int cost,
                                  final String rawDescription,
                                  final CardType type,
                                  final CardColor color,
                                  final CardRarity rarity,
                                  final CardTarget target){

        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.cardsToPreview = this;
        this.showPreview    = true;
    }

//    @Override
//    public void renderCardPreviewInSingleView(SpriteBatch sb) {
//        for (int i = 0; i < linkedCards.size(); i++) {
//            this.linkedCards.get(i).current_x = (485.0F + (linkedCards.size() >= 4 ? this.xSingle4more[i]: this.xSingle[i])) * Settings.scale;
//            this.linkedCards.get(i).current_y = (795.0F + (linkedCards.size() >= 4 ? this.ySingle4more[i]: this.ySingle[i])) * Settings.scale;
//            this.linkedCards.get(i).drawScale = 0.8F;
//            this.linkedCards.get(i).render(sb);
//        }
//    }

//    @Override
//    public void renderCardPreview(SpriteBatch sb) {
//        for (int i = 0; i < linkedCards.size(); i++) {
//            if (AbstractDungeon.player == null || !AbstractDungeon.player.isDraggingCard) {
//                float tmpScale = this.drawScale * 0.8F;
//                if (this.current_x > (float)Settings.WIDTH * 0.75F) {
//                    this.linkedCards.get(i).current_x = (this.current_x + (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.8F + 16.0F) - this.xMulti[i] * Settings.scale);
//                } else {
//                    this.linkedCards.get(i).current_x = (this.current_x - (IMG_WIDTH / 2.0F + IMG_WIDTH / 2.0F * 0.8F + 16.0F) + this.xMulti[i] * Settings.scale);
//                }
//                this.linkedCards.get(i).current_y = (this.current_y + (IMG_HEIGHT / 2.0F - IMG_HEIGHT / 2.0F * 0.8F) + this.yMulti[i] * Settings.scale);
//                this.linkedCards.get(i).drawScale = tmpScale;
//                this.linkedCards.get(i).render(sb);
//            }
//        }
//    }

    @Override
    public void update() {
        super.update();
        if (hb.hovered && this.showPreview) {
            if (rotationTimer <= 0F) {
                rotationTimer = 2F;
                if (!linkedCards.isEmpty()) {
                    cardsToPreview = linkedCards.get(previewIndex);
                }
                if (previewIndex == linkedCards.size() - 1) {
                    previewIndex = 0;
                } else {
                    previewIndex++;
                }
            } else {
                rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }
}