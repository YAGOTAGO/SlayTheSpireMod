package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;

import static YagoMod.DefaultMod.makeCardPath;

public class SurrenderFlesh extends AbstractDynamicCard {

    /*
     * Draw 3(4) cards. Lose 2 max HP.
     */

    public static final String ID = DefaultMod.makeID(SurrenderFlesh.class.getSimpleName());
    public static final String IMG = makeCardPath("SurrenderFlesh.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int UPGRADE_PLUS_DRAW = 1;
    private static final int MAX_HP_LOSS = 2;
    private static final int DRAW = 3;

    public SurrenderFlesh() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DRAW;

        //WARNING: using baseDamage so easier to make json.
        baseDamage = MAX_HP_LOSS;
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new OfferingEffect(), 0.1F));
        } else {
            this.addToBot(new VFXAction(new OfferingEffect(), 0.5F));
        }
        AbstractDungeon.player.decreaseMaxHealth(MAX_HP_LOSS);
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DRAW);
            initializeDescription();
        }
    }
}
