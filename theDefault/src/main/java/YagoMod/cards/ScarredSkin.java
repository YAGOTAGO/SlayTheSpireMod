package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class ScarredSkin extends AbstractDynamicCard {

    /*
     * (1) Gain block equal to (double) damage you took this turn.
     */

    public static final String ID = DefaultMod.makeID(ScarredSkin.class.getSimpleName());
    public static final String IMG = makeCardPath("ScarredSkin.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final int COST = 1;

    public ScarredSkin() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int damageTakenThisTurn = GameActionManager.damageReceivedThisTurn;

        if(!upgraded){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, damageTakenThisTurn));
        }else{
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 2* damageTakenThisTurn));
        }

    }

    public AbstractCard makeCopy() {return new ScarredSkin();}

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
