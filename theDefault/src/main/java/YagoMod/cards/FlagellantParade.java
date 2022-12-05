package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import YagoMod.powers.FlagellantParadePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class FlagellantParade extends AbstractDynamicCard {

    /*
     * (1) Whenever you lose HP from a card, deal 5(8) damage to a random enemy.
     */

    public static final String ID = DefaultMod.makeID(FlagellantParade.class.getSimpleName());
    public static final String IMG = makeCardPath("FlagellantParade.png");
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 3;
    private static final int MAGIC = 1;

    // /STAT DECLARATION/

    public FlagellantParade() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        this.baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlagellantParadePower(p, p, 1, DAMAGE), MAGIC));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_UPGRADE);
            initializeDescription();
        }
    }

}
