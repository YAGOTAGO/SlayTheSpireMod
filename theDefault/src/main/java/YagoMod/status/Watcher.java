package YagoMod.status;

import YagoMod.DefaultMod;
import YagoMod.actions.DiscoverAction;
import YagoMod.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class Watcher extends AbstractDynamicCard {

    /*
     * Option card: Discover a Purple color card
     */

    public static final String ID = DefaultMod.makeID(Watcher.class.getSimpleName());
    public static final String IMG = makeCardPath("Watcher.jpg");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = CardColor.PURPLE;

    private static final int COST = -2;

    public Watcher() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }
    public void onChoseThisOption() {
        AbstractDungeon.actionManager.addToBottom(new DiscoverAction(CardColor.PURPLE, false, upgraded));
    }

    public AbstractCard makeCopy() {return new Watcher();}

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
