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
    public static final String IMG = makeCardPath("Exsanguinate.jpg");// "public static final String IMG = makeCardPath("${NAME}.png");

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 4;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int DRAW_AMOUNT = 1;

    // /STAT DECLARATION/

    public Exsanguinate() {
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
