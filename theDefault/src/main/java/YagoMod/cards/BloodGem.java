package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static YagoMod.DefaultMod.makeCardPath;

public class BloodGem extends AbstractDynamicCard {

    /*
     * (0) Gain 1 energy, discard 1 card, lose 3 HP (and draw a card).
     */

    public static final String ID = DefaultMod.makeID(BloodGem.class.getSimpleName());
    public static final String IMG = makeCardPath("BloodGem.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int HP_LOSS = 3;
    private static final int DRAW = 1;
    private static final int ENERGY_GAIN = 1;

    public BloodGem() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 1, false));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, HP_LOSS));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGY_GAIN));

        if(upgraded)
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(DRAW));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
