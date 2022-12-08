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

public class Silent extends AbstractDynamicCard {

    /*
     * Option card: Discover a Green color card
     */

    public static final String ID = DefaultMod.makeID(Silent.class.getSimpleName());
    public static final String IMG = makeCardPath("Silent.jpg");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = CardColor.GREEN;

    private static final int COST = -2;

    public Silent() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }
    public void onChoseThisOption() {
        AbstractDungeon.actionManager.addToBottom(new DiscoverAction(CardColor.GREEN, false, upgraded));
    }

    public AbstractCard makeCopy() {return new Silent();}

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
