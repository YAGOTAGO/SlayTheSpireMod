package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.InfernoAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static YagoMod.DefaultMod.makeCardPath;

public class Inferno extends AbstractDynamicCard {

    /*
     * Unplayable. If this card is discarded, deal 4(6) damage to all enemies. Increase the damage of ALL Inferno cards by 2 this combat.
     */

    public static final String ID = DefaultMod.makeID(Inferno.class.getSimpleName());
    public static final String IMG = makeCardPath("InfernoArt.jpg");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = -2;
    private static final int DAMAGE = 4;

    //For upgrade
    private static final int DAMAGE_PLUS= 2;

    //Increment is for damage increase this combat
    private static final int INCREMENT_AMOUNT = 2; //half the amount because its trigerring twice
    public Inferno() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        isMultiDamage = true;
        this.baseMagicNumber = INCREMENT_AMOUNT;
        this.magicNumber = this.baseMagicNumber;

    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Attack
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_FIRE"));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));

        //Increment the damage
        AbstractDungeon.actionManager.addToBottom(new InfernoAction(this, this.magicNumber));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "Cannot play this card.";
        return false;
    }

    public void triggerOnManualDiscard() {
        //Deal damage to all enemies
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        //Increment the damage
        AbstractDungeon.actionManager.addToBottom(new InfernoAction(this, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Inferno();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_PLUS);
            initializeDescription();
        }
    }
}
