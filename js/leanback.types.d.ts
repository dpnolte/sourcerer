/* generated @ 2018-11-10T13:56:09.420 */
/// <reference path='./main.types.d.ts' />
/// <reference path='./recyclerview.types.d.ts' />

declare namespace LeanbackTypes {
  export interface BaseCardViewAttributes extends MainTypes.FrameLayoutAttributes {
    lbcardBackground?: number;
    lbcardForeground?: number;
    lbcardType?: number;
    lbextraVisibility?: number;
    lbinfoVisibility?: number;
  }

  export interface BaseCardViewLayoutParamsAttributes extends MainTypes.FrameLayoutLayoutParamsAttributes {
    lblayout_viewType?: number;
  }

  export interface BaseGridViewAttributes extends RecyclerviewTypes.RecyclerViewAttributes {
    lbandroid_gravity?: number;
    lbandroid_horizontalSpacing?: string;
    lbandroid_verticalSpacing?: string;
  }

  export interface BrowseFrameLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  export interface GuidedActionEditTextAttributes extends MainTypes.EditTextAttributes {
  }

  export interface HorizontalGridViewAttributes extends BaseGridViewAttributes {
    lbnumberOfRows?: number;
    lbrowHeight?: number;
  }

  export interface ImageCardViewAttributes extends BaseCardViewAttributes {
    lbinfoAreaBackground?: number;
  }

  export interface ListRowHoverCardViewAttributes extends MainTypes.LinearLayoutAttributes {
  }

  export interface ListRowViewAttributes extends MainTypes.LinearLayoutAttributes {
  }

  export interface PickerAttributes extends MainTypes.FrameLayoutAttributes {
  }

  export interface RowHeaderViewAttributes extends MainTypes.TextViewAttributes {
  }

  export interface SearchBarAttributes extends MainTypes.RelativeLayoutAttributes {
  }

  export interface SearchEditTextAttributes extends MainTypes.EditTextAttributes {
  }

  export interface SearchOrbViewAttributes extends MainTypes.FrameLayoutAttributes {
    lbsearchOrbIcon?: number;
  }

  export interface ShadowOverlayContainerAttributes extends MainTypes.FrameLayoutAttributes {
  }

  export interface SpeechOrbViewAttributes extends SearchOrbViewAttributes {
  }

  export interface TimePickerAttributes extends PickerAttributes {
    lbis24HourFormat?: boolean;
  }

  export interface TitleViewAttributes extends MainTypes.FrameLayoutAttributes {
  }

  export interface VerticalGridViewAttributes extends BaseGridViewAttributes {
    lbcolumnWidth?: number;
    lbnumberOfColumns?: number;
  }
}
