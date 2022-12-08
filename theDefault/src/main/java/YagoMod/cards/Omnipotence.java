package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import YagoMod.status.*;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static YagoMod.DefaultMod.makeCardPath;

public class Omnipotence extends AbstractDynamicCard {

    /*
     * (1) Choose a color to discover a card from. It costs 1 less this turn.
     */

    public static final String ID = DefaultMod.makeID(Omnipotence.class.getSimpleName());
    public static final String IMG = makeCardPath("Omnipotence.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    public Omnipotence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> choices = new ArrayList();
        choices.add(new Ironclad());
        choices.add(new Silent());
        choices.add(new Watcher());
        choices.add(new Defect());

        if (this.upgraded) {
            for (AbstractCard c : choices) {
                c.upgrade();
            }
        }

        this.addToBot(new ChooseOneAction(choices));
    }

    public AbstractCard makeCopy() {return new Omnipotence();}

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
