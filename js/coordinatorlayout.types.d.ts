/* generated @ 2018-11-09T18:38:59.432 */
/// <reference path='./main.types.d.ts' />

declare namespace CoordinatorlayoutTypes {
  interface CoordinatorLayoutAttributes extends MainTypes.ViewGroupAttributes {
  }

  interface CoordinatorLayoutLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
    android_layout_gravity?: number;
    layout_anchor?: string;
    layout_anchorGravity?: any;
    layout_behavior?: string;
    layout_dodgeInsetEdges?: string;
    layout_insetEdge?: LayoutInsetEdgeEnum;
    layout_keyline?: number;
  }

  enum LayoutInsetEdgeEnum { 'none', 'top', 'bottom', 'left', 'right', 'start', 'end' }
}
