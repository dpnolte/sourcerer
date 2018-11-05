/* generated @ 2018-11-05T13:39:01.656 */
import "./generated.attributes";

declare module "LayoutTypes" {
  interface ActionBarAttributes extends RelativeLayoutAttributes {
  }

  interface CardViewAttributes extends FrameLayoutAttributes {
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

  interface ColumnCardViewAttributes extends CardViewAttributes {
    columnSpan?: number;
  }

  enum DayNightStyleEnum { 'auto', 'auto_inverse', 'always_light', 'always_dark' }

  enum GutterEnum { 'none', 'start', 'end', 'both' }

  interface PagedListViewAttributes extends FrameLayoutAttributes {
    dayNightStyle?: DayNightStyleEnum;
    downButtonIcon?: string;
    gutter?: GutterEnum;
    gutterSize?: string;
    scrollBarColor?: string;
    scrollBarContainerWidth?: string;
    scrollBarTopMargin?: string;
    upButtonIcon?: string;
  }

  interface PagedScrollBarViewAttributes extends ViewGroupAttributes {
  }
}
