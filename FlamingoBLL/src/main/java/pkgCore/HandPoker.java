package pkgCore;

import java.util.ArrayList;
import java.util.Collections;
import pkgConstants.*;
import pkgEnum.eCardNo;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class HandPoker extends Hand {

	private ArrayList<CardRankCount> CRC = null;

	public HandPoker() {
		this.setHS(new HandScorePoker());
	}

	protected ArrayList<CardRankCount> getCRC() {
		return CRC;
	}

	@Override
	public HandScore ScoreHand() {
		// TODO : Implement this method... call each of the 'is' methods (isRoyalFlush,
		// etc) until
		// one of the hands is true, then score the hand

		Collections.sort(super.getCards());
		Frequency();

		if (isRoyalFlush()) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.RoyalFlush);
		} 
		else if (isStraightFlush()) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.StraightFlush);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		else if (isFourOfAKind()) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.FourOfAKind);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		
		else if (isFullHouse()) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.FullHouse);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		
		else if (isFlush()) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.Flush);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		
		else if (isStraight() == true) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.Straight);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		
		else if (isThreeOfAKind()) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.ThreeOfAKind);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		
		else if (isTwoPair()) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.TwoPair);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		
		else if (isPair()) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.Pair);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		
		else {
			isHighCard();
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.HighCard);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		
		return null;
	}

	public boolean isRoyalFlush() {
		boolean bIsRoyalFlush = false;
		
		int iCardCnt = super.getCards().size();

		if (isFlush() == true) {
			for (eRank eRank : eRank.values()) {
				for (Card c : super.getCards()) {
					if (super.getCards().get(0).geteRank() == eRank.ACE && super.getCards().get(1).geteRank() == eRank.KING && super.getCards().get(2).geteRank() == eRank.QUEEN && super.getCards().get(3).geteRank() == eRank.JACK && super.getCards().get(4).geteRank() == eRank.TEN) {
						return bIsRoyalFlush = true;
					}
					else {
						return false;
					}
			}
		}
		}
		return bIsRoyalFlush;
	}

	public boolean isStraightFlush() {
		boolean bisStraightFlush = false;
		if(isRoyalFlush() == false) {
			if (isFlush() == true) {
				if(isStraight() == true) {
					return bisStraightFlush = true;
				}
			}
		}
		return bisStraightFlush;
	}
	
	public boolean isFourOfAKind() {
		boolean bisFourOfAKind = false;
		if (super.getCards().get(0).geteRank().getiRankNbr() == super.getCards().get(1).geteRank().getiRankNbr() && super.getCards().get(0).geteRank().getiRankNbr() == super.getCards().get(2).geteRank().getiRankNbr() && super.getCards().get(0).geteRank().getiRankNbr() == super.getCards().get(3).geteRank().getiRankNbr()) {
				return true;
	}
		else if (super.getCards().get(4).geteRank().getiRankNbr() == super.getCards().get(1).geteRank().getiRankNbr() && super.getCards().get(4).geteRank().getiRankNbr() == super.getCards().get(2).geteRank().getiRankNbr() && super.getCards().get(4).geteRank().getiRankNbr() == super.getCards().get(3).geteRank().getiRankNbr()) {
			return true;
	}
		return bisFourOfAKind;
	}

	public boolean isFullHouse() {
		boolean bisFullHouse = false;
		if (CRC.size() == 2) {
			if(CRC.get(0).getiCnt() == 3 && (CRC.get(1).getiCnt() == 2)){
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.FullHouse);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				return true;			
			}
		}
		return bisFullHouse;

	}

	public boolean isFlush() {
		boolean bisFlush = false;

		int iCardCnt = super.getCards().size();
		int iSuitCnt = 0;
 
		for (eSuit eSuit : eSuit.values()) {
			for (Card c : super.getCards()) {
				if (eSuit == c.geteSuit()) {
					iSuitCnt++;
				}
			}
			if (iSuitCnt > 0)
				break;
		}

		if (iSuitCnt == iCardCnt)
			bisFlush = true;
		else
			bisFlush = false;

		return bisFlush;
	}

	public boolean isStraight() {
		boolean bisStraight = true;
		int a = 0;
		if (super.getCards().get(0).geteRank() == eRank.ACE)
		{
			a=1;
		}
		for (; a < super.getCards().size()-1; a++) {
			if (super.getCards().get(a).geteRank().getiRankNbr() -1  != super.getCards().get(a+1).geteRank().getiRankNbr()) {
				return false;
			}
		}					
		return bisStraight;
	}
	// This is how to implement one of the 'counting' hand types. Testing to see if
	// there are three of the same rank.
	public boolean isThreeOfAKind() {
		boolean bisThreeOfAKind = false;
		if (this.getCRC().size() == 3) {
			if (this.getCRC().get(0).getiCnt() == Constants.THREE_OF_A_KIND) {
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.ThreeOfAKind);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				return true;
			}
		}
		return bisThreeOfAKind;
	}

	public boolean isTwoPair() {
		boolean bisTwoPair = false;
			if(super.getCards().get(0).geteRank().getiRankNbr() ==  super.getCards().get(1).geteRank().getiRankNbr() && super.getCards().get(2).geteRank().getiRankNbr() == super.getCards().get(3).geteRank().getiRankNbr()){
				return true;
			}
			else if(super.getCards().get(1).geteRank().getiRankNbr() ==  super.getCards().get(2).geteRank().getiRankNbr() && super.getCards().get(3).geteRank().getiRankNbr() == super.getCards().get(4).geteRank().getiRankNbr()){
				return true;
			}
			else if(super.getCards().get(0).geteRank().getiRankNbr() ==  super.getCards().get(1).geteRank().getiRankNbr() && super.getCards().get(3).geteRank().getiRankNbr() == super.getCards().get(4).geteRank().getiRankNbr()){
				return true;
			}
		return bisTwoPair;
	}
	
	public boolean isPair() {
		boolean bisPair = false;
		if(super.getCards().get(0).geteRank().getiRankNbr() ==  super.getCards().get(1).geteRank().getiRankNbr()){
			return true;
		}
		else if(super.getCards().get(1).geteRank().getiRankNbr() ==  super.getCards().get(2).geteRank().getiRankNbr()){
			return true;
		}
		else if (super.getCards().get(2).geteRank().getiRankNbr() == super.getCards().get(3).geteRank().getiRankNbr()) {
			return true;
		}
		else if (super.getCards().get(3).geteRank().getiRankNbr() == super.getCards().get(4).geteRank().getiRankNbr()) {
			return true;
		}
		HandScorePoker HSP = (HandScorePoker) this.getHS();
		HSP.seteHandStrength(eHandStrength.Pair);
		int iGetCard = this.getCRC().get(0).getiCardPosition();
		HSP.setHiCard(this.getCards().get(iGetCard));
		HSP.setLoCard(null);
		HSP.setKickers(FindTheKickers(this.getCRC()));
		this.setHS(HSP);
		return bisPair;
	}

	public boolean isHighCard() {
		boolean bisHighCard = true;
		// TODO : Implement this method
		super.getCards().get(0).geteRank().getiRankNbr();
		return bisHighCard;
	}

	private ArrayList<Card> FindTheKickers(ArrayList<CardRankCount> CRC) {
		ArrayList<Card> kickers = new ArrayList<Card>();

		for (CardRankCount crcCheck : CRC) {
			if (crcCheck.getiCnt() == 1) {
				kickers.add(this.getCards().get(crcCheck.getiCardPosition()));
			}
		}

		return kickers;
	}

	private void Frequency() {
		CRC = new ArrayList<CardRankCount>();
		int iCnt = 0;
		int iPos = 0;
		for (eRank eRank : eRank.values()) {
			iCnt = (CountRank(eRank));
			if (iCnt > 0) {
				iPos = FindCardRank(eRank);
				CRC.add(new CardRankCount(eRank, iCnt, iPos));
			}
		}
		Collections.sort(CRC);
	}

	private int CountRank(eRank eRank) {
		int iCnt = 0;
		for (Card c : super.getCards()) {
			if (c.geteRank() == eRank) {
				iCnt++;
			}
		}
		return iCnt;
	}

	private int FindCardRank(eRank eRank) {
		int iPos = 0;

		for (iPos = 0; iPos < super.getCards().size(); iPos++) {
			if (super.getCards().get(iPos).geteRank() == eRank) {
				break;
			}
		}
		return iPos;
	}

}
