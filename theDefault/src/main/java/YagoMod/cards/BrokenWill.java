package YagoMod.cards;

import YagoMod.DefaultMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static YagoMod.DefaultMod.makeCardPath;

public class BrokenWill extends AbstractDynamicCard {

    /*
     * Option card: Randomly spread 4 Weak between enemies.
     */

    public static final String ID = DefaultMod.makeID(BrokenWill.class.getSimpleName());
    public static final String IMG = makeCardPath("WeakOption.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = -2;
    private static final int WEAK_AMOUNT = 4;
    private static final int WEAK_UPG = 4;

    public BrokenWill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = WEAK_AMOUNT;
        magicNumber = baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }
    public void onChoseThisOption() {
        for(int i =0; i<this.magicNumber; i++){
            AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo ,AbstractDungeon.player, new WeakPower(mo, 1,false), 1));
        }
    }

    public AbstractCard makeCopy() {return new BrokenWill();}

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(WEAK_UPG);
            initializeDescription();
        }
    }
}
