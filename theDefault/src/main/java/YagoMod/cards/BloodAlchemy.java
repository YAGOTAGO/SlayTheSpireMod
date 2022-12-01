package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import YagoMod.powers.BloodAlchemyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class BloodAlchemy extends AbstractDynamicCard {

    /*
     * (3) -> (2) At the start of your take 1 damage gain a random potion.
     */

    // TEXT DECLARATION
    public static final String ID = DefaultMod.makeID(BloodAlchemy.class.getSimpleName());
    public static final String IMG = makeCardPath("BloodAlchemy.jpg");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;
    private static final int AMOUNT_POTION = 1;

    private static final int HP_LOSS = 1;

    private static final int MAGIC = 1;

    // /STAT DECLARATION/

    public BloodAlchemy() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;

        //Because can generate healing potions
        this.tags.add(CardTags.HEALING);
    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodAlchemyPower(p, p, HP_LOSS, AMOUNT_POTION), MAGIC));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }

}
