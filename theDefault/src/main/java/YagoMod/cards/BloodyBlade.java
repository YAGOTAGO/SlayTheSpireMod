package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class BloodyBlade extends AbstractDynamicCard {
    /*
     * (2): Deal 12 damage, gains damage equal to (twice) the damage you took this combat.
     */
    public static final String ID = DefaultMod.makeID(BloodyBlade.class.getSimpleName());
    public static final String IMG = makeCardPath("BloodyBlade.png");
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 2;
    private static final int DAMAGE = 12;

    public BloodyBlade() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;

        if(upgraded){
            this.baseMagicNumber = GameActionManager.damageReceivedThisCombat * 2;
        }else{
            this.baseMagicNumber = GameActionManager.damageReceivedThisCombat;
        }
        this.magicNumber = this.baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage + (GameActionManager.damageReceivedThisCombat * 2), damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }else{
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage + (GameActionManager.damageReceivedThisCombat), damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    @Override
    public void tookDamage() {
        if(upgraded){
            this.magicNumber = this.baseMagicNumber = 2 * GameActionManager.damageReceivedThisCombat;
        }else {
            this.magicNumber = this.baseMagicNumber = GameActionManager.damageReceivedThisCombat;
        }
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        this.magicNumber = this.baseMagicNumber = 0;
    }

    public AbstractCard makeCopy() {return new BloodyBlade();}

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
