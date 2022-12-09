package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;

import static YagoMod.DefaultMod.makeCardPath;

public class ToxicChemicals extends AbstractDynamicCard {

    /*
     * (1)->(0) Discard a card. Obtain a common potion.
     */

    public static final String ID = DefaultMod.makeID(ToxicChemicals.class.getSimpleName());
    public static final String IMG = makeCardPath("ToxicChemicals.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final int AMOUNT_DISCARD = 1;

    public ToxicChemicals() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardAction(p, p, AMOUNT_DISCARD, false));

        this.addToBot(new VFXAction(new PotionBounceEffect(p.hb.cX, p.hb.cY, p.hb.cX, this.hb.cY), 0.4F));
        AbstractDungeon.actionManager.addToBottom(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.COMMON, true)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
