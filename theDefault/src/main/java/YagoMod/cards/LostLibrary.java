package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static YagoMod.DefaultMod.makeCardPath;

public class LostLibrary extends AbstractDynamicCard {

    /*
     * (1) Gain block equal to hand size (+3).
     */

    public static final String ID = DefaultMod.makeID(LostLibrary.class.getSimpleName());
    public static final String IMG = makeCardPath("LostLibrary.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final int COST = 1;

    private static final int UPGRADED_BLOCK = 3;

    public LostLibrary() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if(!this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p.hand.size()));
        }else {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p.hand.size() + UPGRADED_BLOCK));
        }

    }

    public AbstractCard makeCopy() {return new LostLibrary();}

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
