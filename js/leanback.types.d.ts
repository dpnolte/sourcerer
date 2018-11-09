/* generated @ 2018-11-09T18:39:23.497 */
/// <reference path='./main.types.d.ts' />
/// <reference path='./recyclerview.types.d.ts' />

declare namespace LeanbackTypes {
  interface BaseCardViewAttributes extends MainTypes.FrameLayoutAttributes {
    lbcardBackground?: number;
    lbcardForeground?: number;
    lbcardType?: number;
    lbextraVisibility?: number;
    lbinfoVisibility?: number;
  }

  interface BaseCardViewLayoutParamsAttributes extends MainTypes.FrameLayoutLayoutParamsAttributes {
    lblayout_viewType?: number;
  }

  interface BaseGridViewAttributes extends RecyclerviewTypes.RecyclerViewAttributes {
    lbandroid_gravity?: number;
    lbandroid_horizontalSpacing?: string;
    lbandroid_verticalSpacing?: string;
  }

  interface BrowseFrameLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface GuidedActionEditTextAttributes extends MainTypes.EditTextAttributes {
  }

  interface HorizontalGridViewAttributes extends BaseGridViewAttributes {
    lbnumberOfRows?: number;
    lbrowHeight?: number;
  }

  interface ImageCardViewAttributes extends BaseCardViewAttributes {
    lbinfoAreaBackground?: number;
  }

  interface ListRowHoverCardViewAttributes extends MainTypes.LinearLayoutAttributes {
  }

  interface ListRowViewAttributes extends MainTypes.LinearLayoutAttributes {
  }

  interface PickerAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface RowHeaderViewAttributes extends MainTypes.TextViewAttributes {
  }

  interface SearchBarAttributes extends MainTypes.RelativeLayoutAttributes {
  }

  interface SearchEditTextAttributes extends MainTypes.EditTextAttributes {
  }

  interface SearchOrbViewAttributes extends MainTypes.FrameLayoutAttributes {
    lbsearchOrbIcon?: number;
  }

  interface ShadowOverlayContainerAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface SpeechOrbViewAttributes extends SearchOrbViewAttributes {
  }

  interface TimePickerAttributes extends PickerAttributes {
    lbis24HourFormat?: boolean;
  }

  interface TitleViewAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface VerticalGridViewAttributes extends BaseGridViewAttributes {
    lbcolumnWidth?: number;
    lbnumberOfColumns?: number;
  }
}
