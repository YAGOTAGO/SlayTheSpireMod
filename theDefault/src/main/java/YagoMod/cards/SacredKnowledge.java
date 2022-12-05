package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.SacredKnowledgeAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class SacredKnowledge extends AbstractDynamicCard {

    /**
     * Deal 9(12) damage. If it kills the target play the top card of your draw pile.
     */

    public static final String ID = DefaultMod.makeID(SacredKnowledge.class.getSimpleName());
    public static final String IMG = makeCardPath("SacredKnowledge.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int DAMAGE_PLUS = 3;

    public SacredKnowledge() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Deal damage
        AbstractDungeon.actionManager.addToBottom(new SacredKnowledgeAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    //used by other methods to add a copy to the hand
    public AbstractCard makeCopy() {return new SacredKnowledge();}

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_PLUS);
            initializeDescription();
        }
    }
}
