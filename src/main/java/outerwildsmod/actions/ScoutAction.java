//package outerwildsmod.actions;
//
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.animations.TalkAction;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.Hitbox;
//import com.megacrit.cardcrawl.localization.UIStrings;
//import com.megacrit.cardcrawl.vfx.SpeechBubble;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//public class ScoutAction extends AbstractGameAction {
//    private static final UIStrings uiStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getUIString("InsightAction");
//    public static final String[] TEXT = uiStrings.TEXT;
//
//    private AbstractMonster monster;
//
//    public ScoutAction(AbstractMonster monster) {
//        this.actionType = ActionType.WAIT;
//        this.duration = Settings.ACTION_DUR_FAST;
//        this.monster = monster;
//    }
//
//    @Override
//    public void update() {
//        if (this.duration == Settings.ACTION_DUR_FAST) {
//            String intentText = TEXT[0] + monster.intent.name() + TEXT[1];
//            AbstractCreature player = com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
//            AbstractCreature source = com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
//            AbstractDungeon.effectList.add(new SpeechBubble(source.dialogX, source.dialogY, 3.0F, intentText, true));
//            addToBot(new TalkAction());
//        }
//        tickDuration();
//    }
//}
//
