package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static YagoMod.DefaultMod.makeCardPath;

public class Resurgence extends AbstractDynamicCard {

    /*
     * (1): Gain 5(8) Block.
     */

    public static final String ID = DefaultMod.makeID(Resurgence.class.getSimpleName());
    public static final String IMG = makeCardPath("Defend.png");
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    public Resurgence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int painAmount = p.getPower(DefaultMod.makeID("PainPower")).amount;
        this.addToBot(new RemoveSpecificPowerAction(p, p, DefaultMod.makeID("PainPower")));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, painAmount), 1));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p.powers.contains(p.getPower(DefaultMod.makeID("PainPower")))){
            return true;
        }
        return false;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}
