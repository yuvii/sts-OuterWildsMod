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

    @Override
    public void update() {
        super.update();
        if (!this.showPreview) { cardsToPreview = null; }
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