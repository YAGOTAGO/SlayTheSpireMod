package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class Oblation extends AbstractDynamicCard {

    /*
     * Unplayable. If this card is discarded from your hand, heal 1(2), draw 1.
     */

    public static final String ID = DefaultMod.makeID(Oblation.class.getSimpleName());
    public static final String IMG = makeCardPath("Oblation.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = -2;
    private static final int UPGRADE_PLUS_HEAL = 1;
    private static final int HEAL = 1;
    private static final int DRAW = 1;

    public Oblation() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = HEAL;
        this.tags.add(CardTags.HEALING);
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(DRAW));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "Cannot play this card.";
        return false;
    }

    public void triggerOnManualDiscard() {
        this.addToBot(new DrawCardAction(AbstractDungeon.player, DRAW));
        this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_HEAL);
            initializeDescription();
        }
    }
}
