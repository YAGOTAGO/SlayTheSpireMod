package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.LifestealAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

// public class ${NAME} extends AbstractDynamicCard
public class Exsanguinate extends AbstractDynamicCard {

    /**
     * Lifesteal 6(9) damage, draw a card.
     */

    public static final String ID = DefaultMod.makeID(Exsanguinate.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("${NAME}.png");

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;  // COST = ${COST}
    private static final int DAMAGE = 6;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int DRAW_AMOUNT = 1;

    // /STAT DECLARATION/

    public Exsanguinate() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = DRAW_AMOUNT;
        magicNumber = baseMagicNumber;
        this.tags.add(CardTags.HEALING);

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //Deal damage
        AbstractDungeon.actionManager.addToBottom(new LifestealAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(DRAW_AMOUNT));
    }

    //used by other methods to add a copy to the hand
    public AbstractCard makeCopy() {return new Exsanguinate();}

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
