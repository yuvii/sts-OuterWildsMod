package outerwildsmod.cards.QuantumCards.JetpackBooster;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.AdrenalineEffect;
import outerwildsmod.cards.AbstractCards.AbstractQuantumCard;
import outerwildsmod.character.Hearthian;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;

public class JetpackBoosterDraw extends AbstractQuantumCard {

    private static final CardInfo cardInfo = new CardInfo(
            "JetpackBoosterDraw",
            1,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.RARE,
            Hearthian.Enums.CARD_COLOR
    );

    public final static String ID = makeID(cardInfo.baseId);

    public JetpackBoosterDraw() {
        super(cardInfo);
        this.exhaust = true;
        this.isMainCard = true;

        linkCard(new JetpackBoosterEnergy());
    }

    public void useCard(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new AdrenalineEffect(), 0.15F));

        this.addToBot(new GainEnergyAction(1));
        this.addToBot(new DrawCardAction(p, 2));
    }

    @Override
    public void onSwapIn() {

    }

    @Override
    public void onSwapOut() {

    }

    @Override
    public AbstractCard makeCopy() { return new JetpackBoosterDraw(); }
}
