/* generated @ 2018-11-05T13:38:03.668 */
import "./generated.attributes";
import "./generated-recyclerview.attributes";

declare module "LayoutTypes" {
  interface BaseCardViewAttributes extends FrameLayoutAttributes {
    lbcardBackground?: number;
    lbcardForeground?: number;
    lbcardType?: number;
    lbextraVisibility?: number;
    lbinfoVisibility?: number;
  }

  interface BaseCardViewLayoutParamsAttributes extends FrameLayoutLayoutParamsAttributes {
    lblayout_viewType?: number;
  }

  interface BaseGridViewAttributes extends RecyclerViewAttributes {
    lbandroid_gravity?: number;
    lbandroid_horizontalSpacing?: string;
    lbandroid_verticalSpacing?: string;
  }

  interface BrowseFrameLayoutAttributes extends FrameLayoutAttributes {
  }

  interface GuidedActionEditTextAttributes extends EditTextAttributes {
  }

  interface HorizontalGridViewAttributes extends BaseGridViewAttributes {
    lbnumberOfRows?: number;
    lbrowHeight?: number;
  }

  interface ImageCardViewAttributes extends BaseCardViewAttributes {
    lbinfoAreaBackground?: number;
  }

  interface ListRowHoverCardViewAttributes extends LinearLayoutAttributes {
  }

  interface ListRowViewAttributes extends LinearLayoutAttributes {
  }

  interface PickerAttributes extends FrameLayoutAttributes {
  }

  interface RowHeaderViewAttributes extends TextViewAttributes {
  }

  interface SearchBarAttributes extends RelativeLayoutAttributes {
  }

  interface SearchEditTextAttributes extends EditTextAttributes {
  }

  interface SearchOrbViewAttributes extends FrameLayoutAttributes {
    lbsearchOrbIcon?: number;
  }

  interface ShadowOverlayContainerAttributes extends FrameLayoutAttributes {
  }

  interface SpeechOrbViewAttributes extends SearchOrbViewAttributes {
  }

  interface TimePickerAttributes extends PickerAttributes {
    lbis24HourFormat?: boolean;
  }

  interface TitleViewAttributes extends FrameLayoutAttributes {
  }

  interface VerticalGridViewAttributes extends BaseGridViewAttributes {
    lbcolumnWidth?: number;
    lbnumberOfColumns?: number;
  }
}
