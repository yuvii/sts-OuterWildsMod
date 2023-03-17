package outerwildsmod.cards.abstractCards;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.CommonKeywordIconsField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static outerwildsmod.outerwildsmod.makeID;

public abstract class AbstractModdedCard extends CustomCard  {


    public CardStrings cardStrings;
    public String DESCRIPTION; //The main description of the card
    public String UPGRADE_DESCRIPTION; //The upgrade description of the card, if applicable
    public String[] EXTENDED_DESCRIPTION; //The Norma Effects of the card
    public String NAME; //The base name of the card

    public AbstractModdedCard(final String id,
                              final String img,
                              final int cost,
                              final CardType type,
                              final CardColor color,
                              final CardRarity rarity,
                              final CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);

        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        NAME = cardStrings.NAME;

        initializeDescription();
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

}
