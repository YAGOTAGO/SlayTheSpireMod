package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class VampiricInstinct extends AbstractDynamicCard {

    /*
     * (1) Shuffle 2 (3) Bite into your discard pile, take 3 dmg.
     */

    public static final String ID = DefaultMod.makeID(VampiricInstinct.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int SHUFFLE_AMOUNT = 2;
    private static final int SHUFFLE_PLUS = 1;
    private static final int HP_LOSS= 3;

    public VampiricInstinct() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = SHUFFLE_AMOUNT;
        this.cardsToPreview = new Bite();
        this.exhaust = true;

        //Creates healing cards
        this.tags.add(CardTags.HEALING);
    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.cardsToPreview.makeStatEquivalentCopy(), magicNumber));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p,p, HP_LOSS));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(SHUFFLE_PLUS);
            initializeDescription();
        }
    }
}
