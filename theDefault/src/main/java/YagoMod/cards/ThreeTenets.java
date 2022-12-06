package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.NoCreedAction;
import YagoMod.actions.ThreeTenetsAction;
import YagoMod.characters.TheDefault;
import basemod.patches.com.megacrit.cardcrawl.characters.AbstractPlayer.OnPlayerDamagedHook;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class ThreeTenets extends AbstractDynamicCard {

    /*
     * (1) If you played a skill last add a green card to your hand, if power add blue, if attack add red.
     */

    public static final String ID = DefaultMod.makeID(ThreeTenets.class.getSimpleName());
    public static final String IMG = makeCardPath("3Tenets.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final int COST = 1;

    public ThreeTenets() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ThreeTenetsAction(upgraded));
    }


    //will glow after a card is played
    public void triggerOnGlowCheck() {

        //Must have a card played before
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size()>1){

            AbstractCard.CardType type = AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 2).type;

            //type may not be status or curse
            if(type != CardType.STATUS && type != CardType.CURSE){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }

        }else{
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public AbstractCard makeCopy() {return new ThreeTenets();}

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
