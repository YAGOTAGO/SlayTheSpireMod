package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;


import java.util.Iterator;

import static YagoMod.DefaultMod.makeCardPath;

public class MartyrFlesh extends AbstractDynamicCard {

    /*
     * SKILL:
     * (0) Apply 2 Vulnerable to ALL characters. Apply 1(2) Weak to ALL enemies.
     */

    public static final String ID = DefaultMod.makeID(MartyrFlesh.class.getSimpleName());
    public static final String IMG = makeCardPath("Martyr.png");

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int WEAK_AMOUNT = 1;
    private static final int WEAK_PLUS = 1;
    private static final int VULNERABLE_AMOUNT = 2;

    public MartyrFlesh() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = WEAK_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        //Apply vulnerable to self
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, VULNERABLE_AMOUNT, false)));

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();

            //Apply vulnerable to all enemies
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, VULNERABLE_AMOUNT, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

            //Apply weak to all enemies
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }


    public AbstractCard makeCopy() {return new MartyrFlesh();}

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(WEAK_PLUS);
            initializeDescription();
        }
    }
}
