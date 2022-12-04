package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;


public class PierceTheHeart extends AbstractDynamicCard {
    /*
     * (1): Break 5(8) Block. NL Deal 10(12) damage.
     */

    public static final String ID = DefaultMod.makeID(PierceTheHeart.class.getSimpleName());
    public static final String IMG = makeCardPath("PierceHeart.png");
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 10;
    private static final int BLOCK_BREAK = 5;
    private static final int BLOCK_BREAK_UPGRADE = 3;
    private static final int UPGRADE_PLUS_DMG = 2;


    public PierceTheHeart() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = BLOCK_BREAK;
        this.magicNumber = this.baseMagicNumber;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m.currentBlock>0){
            m.loseBlock(magicNumber);
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    //used by other methods to add a copy to the hand
    public AbstractCard makeCopy() {return new PierceTheHeart();}

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(BLOCK_BREAK_UPGRADE);
            initializeDescription();
        }
    }
}
