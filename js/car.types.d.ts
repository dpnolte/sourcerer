/* generated @ 2018-11-10T13:56:18.137 */
/// <reference path='./main.types.d.ts' />

declare namespace CarTypes {
  export interface ActionBarAttributes extends MainTypes.RelativeLayoutAttributes {
  }

  export interface CardViewAttributes extends MainTypes.FrameLayoutAttributes {
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

  export interface ColumnCardViewAttributes extends CardviewTypes.CardViewAttributes {
    columnSpan?: number;
  }

  export enum DayNightStyleEnum { 'auto', 'auto_inverse', 'always_light', 'always_dark' }

  export enum GutterEnum { 'none', 'start', 'end', 'both' }

  export interface PagedListViewAttributes extends MainTypes.FrameLayoutAttributes {
    dayNightStyle?: DayNightStyleEnum;
    downButtonIcon?: string;
    gutter?: GutterEnum;
    gutterSize?: string;
    scrollBarColor?: string;
    scrollBarContainerWidth?: string;
    scrollBarTopMargin?: string;
    upButtonIcon?: string;
  }

  export interface PagedScrollBarViewAttributes extends MainTypes.ViewGroupAttributes {
  }
}
