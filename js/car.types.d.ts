/* generated @ 2018-11-09T18:39:33.265 */
/// <reference path='./main.types.d.ts' />

declare namespace CarTypes {
  interface ActionBarAttributes extends MainTypes.RelativeLayoutAttributes {
  }

  interface CardViewAttributes extends MainTypes.FrameLayoutAttributes {
    android_minHeight?: string;
    android_minWidth?: string;
    cardBackgroundColor?: string;
    cardCornerRadius?: string;
    cardElevation?: string;
    cardMaxElevation?: string;
    cardPreventCornerOverlap?: boolean;
    cardUseCompatPadding?: boolean;
    contentPaddingBottom?: string;
    contentPaddingLeft?: string;
    contentPaddingRight?: string;
    contentPaddingTop?: string;
  }

  interface ColumnCardViewAttributes extends CardviewTypes.CardViewAttributes {
    columnSpan?: number;
  }

  enum DayNightStyleEnum { 'auto', 'auto_inverse', 'always_light', 'always_dark' }

  enum GutterEnum { 'none', 'start', 'end', 'both' }

  interface PagedListViewAttributes extends MainTypes.FrameLayoutAttributes {
    dayNightStyle?: DayNightStyleEnum;
    downButtonIcon?: string;
    gutter?: GutterEnum;
    gutterSize?: string;
    scrollBarColor?: string;
    scrollBarContainerWidth?: string;
    scrollBarTopMargin?: string;
    upButtonIcon?: string;
  }

  interface PagedScrollBarViewAttributes extends MainTypes.ViewGroupAttributes {
  }
}
